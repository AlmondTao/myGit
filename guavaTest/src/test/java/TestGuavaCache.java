import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.taoqy.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2018/12/10
 * @see [相关类/方法]
 * @since myGit 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {TestGuavaCache.class,})
public class TestGuavaCache {

    public CacheLoader createCacheLoader(){
        return new CacheLoader<String,Person>() {
            @Override
            public Person load(String key) throws Exception {
                System.out.println("加载person");
                return new Person(key, new Random().longs().toString());
            }
        };
    }

    @Test
    public void testBasic() throws InterruptedException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(30L, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());
        Person person1 = cache.getUnchecked("张三");
        System.out.println(person1.toString());
        AtomicLong atomicLong = new AtomicLong(0L);
        atomicLong.getAndIncrement();
        
        TimeUnit.MILLISECONDS.sleep(31);
        Person person2 = cache.getUnchecked("张三");
        System.out.println(person2.toString());

    }

    @Test
    public void testSize() throws ExecutionException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                .maximumSize(3)
                .build(createCacheLoader());
        Person zhangsan = cache.get("张三");
        System.out.println(zhangsan.toString());
        Person lisi = cache.get("李四");
        System.out.println(lisi.toString());
        Person wangwu = cache.get("王五");
        System.out.println(wangwu.toString());

        Person zhaoliu = cache.get("赵六");
        System.out.println(zhaoliu.toString());

        zhangsan = cache.get("张三");
        System.out.println("张三是否被替换了："+(zhangsan==null?"是":"否"));
        System.out.println(zhangsan.toString());
        zhaoliu = cache.get("赵六");
        System.out.println(zhaoliu.toString());
    }

    @Test
    public void testWeight() throws ExecutionException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                .maximumWeight(10)
                .weigher(new Weigher<String, Person>() {

                    @Override
                    public int weigh(String key, Person value) {
                        return key.length();

                    }
                })
                .build(createCacheLoader());

        cache.get("1234");
        System.out.println(cache.size());
        cache.get("123456");
        System.out.println(cache.size());
        cache.get("12345");
        System.out.println(cache.size());
        cache.get("123");
        System.out.println(cache.size());
        cache.get("1234567");
        System.out.println(cache.size());


    }

    /**
     * TTL -> time to live
     * Access time => write/update/read
     * @throws InterruptedException
     */
    @Test
    public void testEvictionByAccessTime() throws InterruptedException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .build(createCacheLoader());

        cache.getUnchecked("zhangsan");
        TimeUnit.SECONDS.sleep(3);
        //IfPresent读取当前的值,若没有则返回null
        Person zhangsan = cache.getIfPresent("zhangsan");
        System.out.println("zhangsan是否被销毁："+(zhangsan == null?"是":"否"));

        cache.getUnchecked("lisi");
        TimeUnit.SECONDS.sleep(2);
        Person lisi = cache.getIfPresent("lisi");
        System.out.println("lisi是否被销毁："+(lisi == null?"是":"否"));

        cache.getUnchecked("lisi");
        TimeUnit.SECONDS.sleep(1);
        //read时也会重置过期时间
        lisi = cache.getIfPresent("lisi");
        System.out.println("lisi是否被销毁："+(lisi == null?"是":"否"));

        TimeUnit.SECONDS.sleep(1);
        lisi = cache.getIfPresent("lisi");
        System.out.println("lisi是否被销毁："+(lisi == null?"是":"否"));
        TimeUnit.SECONDS.sleep(2);
        lisi = cache.getIfPresent("lisi");
        System.out.println("lisi是否被销毁："+(lisi == null?"是":"否"));
//        lisi = cache.getUnchecked("lisi");

    }

    @Test
    public void testEvictionByWriteTime() throws ExecutionException, InterruptedException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(createCacheLoader());

        cache.get("zhangsan");
        TimeUnit.SECONDS.sleep(2);
        Person zhangsan = cache.getIfPresent("zhangsan");
        System.out.println("zhangsan是否被销毁："+(zhangsan == null?"是":"否"));

        cache.get("zhangsan");
        TimeUnit.SECONDS.sleep(1);
        zhangsan = cache.getIfPresent("zhangsan");
        System.out.println("zhangsan是否被销毁："+(zhangsan == null?"是":"否"));

        TimeUnit.SECONDS.sleep(1);
        zhangsan = cache.getIfPresent("zhangsan");
        System.out.println("zhangsan是否被销毁："+(zhangsan == null?"是":"否"));
    }

    @Test
    public void testWeakKey() throws InterruptedException {
        LoadingCache<String, Person> cache = CacheBuilder
                .newBuilder()
                //guava 封装了weakreference和softreference
                .weakKeys()
                .weakValues()
                .build(createCacheLoader());
        cache.getUnchecked("zhangsan");
        Person lisi = cache.getUnchecked("lisi");

        System.gc();
        TimeUnit.MILLISECONDS.sleep(100);
        Person zhangsan = cache.getIfPresent("zhangsan");
        lisi = cache.getIfPresent("lisi");
        System.out.println("zhangsan是否被销毁："+(zhangsan == null?"是":"否"));
        System.out.println("lisi是否被销毁："+(lisi == null?"是":"否"));

    }

}

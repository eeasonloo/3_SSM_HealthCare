package org.eeasonloo.jobs;

import org.eeasonloo.entity.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){

        Set<String> picNameSetToBeDeleted = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
                                                                RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        if(picNameSetToBeDeleted != null){
            for (String picNotLinked : picNameSetToBeDeleted) {
                File picDelete = new File("C:\\my_java\\repository\\healthcare_parent\\healthcare_backend\\src\\main\\webapp\\upload"
                        + picNotLinked );
                picDelete.delete();

                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picNotLinked);
            }

        }
    }
}

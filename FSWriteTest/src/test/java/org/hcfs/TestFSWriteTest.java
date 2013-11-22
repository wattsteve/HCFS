package org.hcfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;

public class TestFSWriteTest{

    @Test
    public void test() throws Exception{
        FSWriteTest.writeToDFS(111, "afile.txt");
        Assert.assertEquals(
                FileSystem.get(new Configuration()).getFileStatus(new Path("afile.txt")).getLen(),
                111L);
    }
}

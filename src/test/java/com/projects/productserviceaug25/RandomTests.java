package com.projects.productserviceaug25;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTests {
    @Test
    public void sampleTest(){
        //arrange
        int a =1;
        int b =2;

        //act

        int result = a+b;

        //assert
        assert result==3;

        assertEquals(a,b);

    }
}

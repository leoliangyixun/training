package test;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

/**
 * @author yangkai
 * @date 2020/9/24
 * @email yangkai@hujiang.com
 * @description
 */
public class TestSort {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppDto {

        private Integer appId;
        private String name;
        private String avatar;
        private String description;
        private Integer level;

    }

    @Test
    public void testSortApp() {
        List<AppDto> list1 = Lists.newArrayList(
            new AppDto(1,"1", null, null, 0),
            new AppDto(2,"2", null, null, 1),
            new AppDto(3,"3", null, null, 200),
            new AppDto(4,"4", null, null, 2)

        );

        list1.sort(Comparator.comparing(e -> e.getLevel(), (o1,o2) -> Integer.compare(o2,o1)));
        for (AppDto app : list1) {
            System.out.println(app);
        }
        System.out.println("-------------");
        List<AppDto> list2 = Lists.newArrayList(
            new AppDto(1,"1", null, null, 0),
            new AppDto(2,"2", null, null, 1),
            new AppDto(3,"3", null, null, 200),
            new AppDto(4,"4", null, null, 2)

        );
        list2.sort(Comparator.comparingInt(e -> e.getLevel()));
        for (AppDto app : list2) {
            System.out.println(app);
        }
        System.out.println("-------------");
    }

}

package exchange.core2.core.orderbook;

import exchange.core2.core.common.Order;
import org.junit.Test;

import java.util.Collections;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MapTest {

    @Test
    public void test_mapHeadSort() {
        // Ask: sell, 卖单队列，需要升序排序，卖价低的在前边
        NavigableMap<Long, OrdersBucketNaive> askBuckets = new TreeMap<>();

        // Bid: buy，买单队列，需要降序排序，买价高的在前边，treeMap 默认是升序
        NavigableMap<Long, OrdersBucketNaive> bidBuckets = new TreeMap<>(Collections.reverseOrder());

        OrdersBucketNaive ordersBucketNaive = new OrdersBucketNaive(1000);
        ordersBucketNaive.put(Order.builder().orderId(10).uid(888).size(10).build());
        ordersBucketNaive.put(Order.builder().orderId(11).uid(777).size(50).build());
        ordersBucketNaive.put(Order.builder().orderId(13).uid(999).size(2).build());

        OrdersBucketNaive ordersBucketNaive2 = new OrdersBucketNaive(999);
        ordersBucketNaive2.put(Order.builder().orderId(10).uid(888).size(10).build());
        ordersBucketNaive2.put(Order.builder().orderId(11).uid(777).size(50).build());
        ordersBucketNaive2.put(Order.builder().orderId(13).uid(999).size(2).build());

        OrdersBucketNaive ordersBucketNaive3 = new OrdersBucketNaive(1001);
        ordersBucketNaive3.put(Order.builder().orderId(10).uid(888).size(10).build());
        ordersBucketNaive3.put(Order.builder().orderId(11).uid(777).size(50).build());
        ordersBucketNaive3.put(Order.builder().orderId(13).uid(999).size(2).build());

        askBuckets.put(ordersBucketNaive.getPrice(), ordersBucketNaive);
        askBuckets.put(ordersBucketNaive2.getPrice(), ordersBucketNaive2);
        askBuckets.put(ordersBucketNaive3.getPrice(), ordersBucketNaive3);

        NavigableMap<Long, OrdersBucketNaive> headMap = askBuckets.headMap(1000L, true);
        System.out.println(headMap);

        bidBuckets.put(ordersBucketNaive.getPrice(), ordersBucketNaive);
        bidBuckets.put(ordersBucketNaive2.getPrice(), ordersBucketNaive2);
        bidBuckets.put(ordersBucketNaive3.getPrice(), ordersBucketNaive3);
        NavigableMap<Long, OrdersBucketNaive> headMap1 = bidBuckets.headMap(1000L, true);
        System.out.println(headMap1);
    }
}

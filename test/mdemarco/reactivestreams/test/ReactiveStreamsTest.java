package mdemarco.reactivestreams.test;

import mdemarco.reactivestreams.SubscriberImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ReactiveStreamsTest {

    @Test
    public void publish_subscribe_shouldconsumeallelements() throws InterruptedException {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        SubscriberImpl<String> subscriber = new SubscriberImpl<>();
        publisher.subscribe(subscriber);

        List<String> items = List.of("a", "b", "c");
        items.forEach(publisher::submit);

        publisher.close();

        Thread.sleep(1000);

        Assert.assertEquals(subscriber.wannabeStorage, items);

    }
}



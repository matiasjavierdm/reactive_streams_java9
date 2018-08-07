package mdemarco.reactivestreams;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class SubscriberImpl<T> implements Subscriber<T> {

    private static Logger logger = LogManager.getLogger(SubscriberImpl.class);

    private Subscription subscription;

    public List<T> wannabeStorage = new LinkedList<>();


    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        logger.info("element: ", item);
        wannabeStorage.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error!", t);
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        logger.debug("Subscriber done");
    }
}
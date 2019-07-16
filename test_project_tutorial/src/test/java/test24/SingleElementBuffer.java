package test24;

/**
 * BoundedBuffer (ограниченный буфер) на один элемент
 *
 */


public class SingleElementBuffer<E> {
    private E elem = null;

    public synchronized void put(E newElem) throws InterruptedException {
        while (elem != null) {
            wait();
        }
        elem = newElem;

        //Нужно разбудить все висящие в wait() потоки, иначе получаем дедлок
        //https://stackoverflow.com/questions/37026/java-notify-vs-notifyall-all-over-again
        notifyAll();
    }

    public synchronized E get() throws InterruptedException {
        while (elem == null) {
            wait();
        }
        E result = elem;
        elem = null;

        //Потребитель только один, поэтому можно будить
        //только один висящий в wait() поток
        notify();
        return result;
    }
}
package test20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
  	Ограничения задачи:

    1) нельзя вызывать методы getValue/setValue/getNext/setNext
        класса Node предварительно не вызвав его метод lock()
    	(предварительно надо заблокировать)
    2) нельзя у заблокированного нода вызвать lock()
    	(нельзя повторно блокировать)
    3) нельзя вызвать метод unlock() у незаблокированного нода
    	(нельзя разблокировать незаблокированное)
    4) нельзя по окoнчанию метода change оставить хотя бы один нод заблокированным
    5) любой из следующих методов класса Node
    	(lock, getValue, setValue, getNext, setNext) 
    	может вызвать исключение InterruptedException. 
    	Если оно было выброшено, но метод ничего не изменил. 
    	Т.е. если метод lock() выбросил исключение, то нод не заблокирован.

    Задание:
    Требуется реализовать метод change(...) 
     (в любом из стилей - рекурсивном или итеративном), 
      который удовлетворяет Ограничениям Задачи и кроме того:
    1) примененный к списку в котором ЕСТЬ oldValue
     - либо заменяет первое вхождение oldValue на newValue 
     и возвращает true, либо, если вызов одного из методов 
     генерирующих InterruptedException выбросил исключение 
     InterruptedException 100 раз подряд, то и метод change 
     выбрасывает InterruptedException (предварительно разблокировав все ноды).
    2) примененный к списку в котором НЕТ oldValue
     - либо доходит до хвоста списка, убеждается что oldValue 
     нет в списке и возвращает false, либо, если вызов одного 
     из методов генерирующих InterruptedException выбросил исключение 
     InterruptedException 100 раз подряд, то и метод change выбрасывает 
     InterruptedException (предварительно разблокировав все ноды).
     
    Заметьте, если некоторый метод выбросил исключение 99 раз подряд, 
    а потом выполнился, то "накопленные исключения - сгорают". 
    Т.е. следующий метод может опять выбросить 99 исключений.
    Будем считать, что возможны только InterruptedException. 
    Не готовить код к другим типам (скажем, наследникам Error и RuntimeException).

 */
public class LinkedListUtils {
	
	@Test
	void testChangeRecurce() throws InterruptedException {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		changeRecurce(node3, 3, 1);
	}

	/**
     * Рекурсивная заготовка ответа (НЕВЕРНАЯ - не отвечает всем требованиям)
     */
    public static boolean changeRecurce(Node tail, int oldValue, int newValue) throws InterruptedException {
        if (tail == null) {
            return false;
        } else {
            try {
                tail.lock();
                if (tail.getValue() == oldValue) {
                    tail.setValue(newValue);
                    return true;
                } else {
                    return changeRecurce(tail.getNext(), oldValue, newValue);
                }
            } finally {
                tail.unlock();
            }
        }
    }
    
    /**
	 * Итеративная заготовка ответа (НЕВЕРНАЯ - не отвечает всем требованиям)
	 */
	public static boolean changeIteration(Node tail, int oldValue, int newValue) throws InterruptedException {
		Node current = tail;
		Node tmpForUnlock = current;
		while (current != null) {
			try {
				current.lock();
				if (current.getValue() == oldValue) {
					current.setValue(newValue);
					return true;
				}
				current = current.getNext();
			} finally {
				tmpForUnlock.unlock();
			}
			tmpForUnlock = current;
		}
		return false;
	}

	@Test
    void testLockNode() throws InterruptedException {
    	Node node = new Node(1, null);
    	node.lock();
    }
    
    @Test
    void testUnLockNode() throws InterruptedException {
    	Node node = new Node(1, null);
    	node.lock();
    	node.unlock();
    }
    
    @Test()
    void testGetNode() throws InterruptedException {
    	Node node = new Node(1, null);
    	node.lock();
    	node.getValue();
    }
    
    @Test
    void testGetUnlockNode()  {
    	Node node = new Node(1, null);
    	assertThrows(InterruptedException.class, node::getValue);
    }
    
    @Test
    void testRepeatLockNode(){
    	Node node = new Node(1, null);

		assertThrows(InterruptedException.class, () -> {
			node.lock();
			node.lock();
		});
    }
    
    @Test
	void testRepeatUnLockNode() {
		Node node = new Node(1, null);
		assertThrows(InterruptedException.class, () -> {
			node.lock();
			node.unlock();
			node.unlock();
		});
	}

	@Test
	void testChangeIterationLockOneNode() throws InterruptedException {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		changeIteration(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	}

	@Test
	void testChangeIterationLockTwoNode() throws InterruptedException {
		Node node1 = new Node(3, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		changeIteration(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	
		node2.lock();
		assertTrue(node2.isLock());
		assertEquals(node2.getValue(), 2);
		node2.unlock();
		assertFalse(node2.isLock());
		
		node1.lock();
		assertTrue(node1.isLock());
		assertEquals(node1.getValue(), 3);
		node1.unlock();
		assertFalse(node1.isLock());
	}

	@Test
	void testChangeIterationUnlockNode() throws InterruptedException {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);

		assertThrows(InterruptedException.class, node3::lock);
		changeIteration(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	}

	@Test
	void testChangeRecurceLockOneNode() throws InterruptedException {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		changeRecurce(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	}

	@Test
	void testChangeRecurceLockTwoNode() throws InterruptedException {
		Node node1 = new Node(3, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		changeRecurce(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	
		node2.lock();
		assertTrue(node2.isLock());
		assertEquals(node2.getValue(), 2);
		node2.unlock();
		assertFalse(node2.isLock());
		
		node1.lock();
		assertTrue(node1.isLock());
		assertEquals(node1.getValue(), 3);
		node1.unlock();
		assertFalse(node1.isLock());
	}

	@Test
	void testChangeRecurceUnlockNode() throws InterruptedException {
		Node node1 = new Node(1, null);
		Node node2 = new Node(2, node1);
		Node node3 = new Node(3, node2);
		assertThrows(InterruptedException.class, node3::lock);
		changeRecurce(node3, 3, 1);
		
		assertFalse(node3.isLock());
		assertFalse(node2.isLock());
		assertFalse(node1.isLock());
		
		node3.lock();
		assertTrue(node3.isLock());
		assertEquals(node3.getValue(), 1);
		node3.unlock();
		assertFalse(node3.isLock());
	}
}
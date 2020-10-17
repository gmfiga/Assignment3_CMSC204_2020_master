
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BasicDoubleLinkedList_STUDENT_Test {

    BasicDoubleLinkedList<Anime> linkedAnime;
    AnimeComparator comparatorAnime;

    public Anime a = new Anime("Fairy Tail", "Hiro Mashima", 2006);
    public Anime b = new Anime("Jo Jo's Bizarre Adventure", "Hirohiko Araki", 1987);
    public Anime c = new Anime("Hunter x Hunter", "Yoshihiro Togashi", 1998);
    public Anime d = new Anime("Sword Art Online", "Reki Kawahara", 2002);

    @Before
    public void setUp() throws Exception {

        linkedAnime = new BasicDoubleLinkedList<Anime>();
        linkedAnime.addToEnd(b);
        linkedAnime.addToEnd(c);
        comparatorAnime = new AnimeComparator();
    }

    @After
    public void tearDown() throws Exception {
        linkedAnime = null;
    }

    @Test
    public void testGetSize() {

        assertEquals(2, linkedAnime.getSize());
    }

    @Test
    public void testAddToEnd() {
        assertEquals(c, linkedAnime.getLast());
        linkedAnime.addToEnd(d);
        assertEquals(d, linkedAnime.getLast());
    }

    @Test
    public void testAddToFront() {
        assertEquals(b, linkedAnime.getFirst());
        linkedAnime.addToFront(a);
        assertEquals(a, linkedAnime.getFirst());
    }

    @Test
    public void testGetFirst() {
        assertEquals(b, linkedAnime.getFirst());
        linkedAnime.addToFront(a);
        assertEquals(a, linkedAnime.getFirst());
    }

    @Test
    public void testGetLast() {
        assertEquals(c, linkedAnime.getLast());
        linkedAnime.addToEnd(d);
        assertEquals(d, linkedAnime.getLast());
    }

    @Test
    public void testToArrayList() {
        ArrayList<Anime> list;
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        list = linkedAnime.toArrayList();
        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
        assertEquals(d, list.get(3));
    }

    @Test
    public void testIteratorSuccessfulNext() {
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        ListIterator<Anime> iteratorCar = linkedAnime.iterator();
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());
    }

    @Test
    public void testIteratorSuccessfulPrevious() {
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        ListIterator<Anime> iteratorCar = linkedAnime.iterator();
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(d, iteratorCar.next());
        assertEquals(true, iteratorCar.hasPrevious());
        assertEquals(d, iteratorCar.previous());
        assertEquals(c, iteratorCar.previous());
        assertEquals(b, iteratorCar.previous());
        assertEquals(a, iteratorCar.previous());
    }

    @Test
    public void testIteratorNoSuchElementExceptionNext() {
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        ListIterator<Anime> iteratorCar = linkedAnime.iterator();
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());

        try {
            //no more elements in list
            iteratorCar.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }

    }

    @Test
    public void testIteratorNoSuchElementExceptionPrevious() {
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        ListIterator<Anime> iteratorCar = linkedAnime.iterator();
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(d, iteratorCar.next());
        assertEquals(true, iteratorCar.hasPrevious());
        assertEquals(d, iteratorCar.previous());
        assertEquals(c, iteratorCar.previous());
        assertEquals(b, iteratorCar.previous());
        assertEquals(a, iteratorCar.previous());

        try {
            //no more elements in list
            iteratorCar.previous();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }

    }

    @Test
    public void testIteratorUnsupportedOperationException() {
        linkedAnime.addToFront(a);
        linkedAnime.addToEnd(d);
        ListIterator<Anime> iteratorCar = linkedAnime.iterator();
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(a, iteratorCar.next());
        assertEquals(b, iteratorCar.next());
        assertEquals(c, iteratorCar.next());
        assertEquals(true, iteratorCar.hasNext());
        assertEquals(d, iteratorCar.next());

        try {
            //remove is not supported for the iterator
            iteratorCar.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }

    }

    @Test
    public void testRemove() {
        // remove the first
        assertEquals(b, linkedAnime.getFirst());
        assertEquals(c, linkedAnime.getLast());
        linkedAnime.addToFront(a);
        assertEquals(a, linkedAnime.getFirst());
        linkedAnime.remove(a, comparatorAnime);
        assertEquals(b, linkedAnime.getFirst());
        //remove from the end of the list
        linkedAnime.addToEnd(d);
        assertEquals(d, linkedAnime.getLast());
        linkedAnime.remove(d, comparatorAnime);
        assertEquals(c, linkedAnime.getLast());
        //remove from middle of list
        linkedAnime.addToFront(a);
        assertEquals(a, linkedAnime.getFirst());
        assertEquals(c, linkedAnime.getLast());
        linkedAnime.remove(b, comparatorAnime);
        assertEquals(a, linkedAnime.getFirst());
        assertEquals(c, linkedAnime.getLast());

    }

    @Test
    public void testRetrieveFirstElement() {
        assertEquals(b, linkedAnime.getFirst());
        linkedAnime.addToFront(a);
        assertEquals(a, linkedAnime.getFirst());
        assertEquals(a, linkedAnime.retrieveFirstElement());
        assertEquals(b, linkedAnime.getFirst());
        assertEquals(b, linkedAnime.retrieveFirstElement());
        assertEquals(c, linkedAnime.getFirst());
    }

    @Test
    public void testRetrieveLastElement() {
        assertEquals(c, linkedAnime.getLast());
        linkedAnime.addToEnd(d);
        assertEquals(d, linkedAnime.getLast());
        assertEquals(d, linkedAnime.retrieveLastElement());
        assertEquals(c, linkedAnime.getLast());

    }

    private class AnimeComparator implements Comparator<Anime> {

        @Override
        public int compare(Anime arg0, Anime arg1) {
            return arg0.toString().compareTo(arg1.toString());
        }

    }

    private class Anime {
        String title;
        String author;
        int year;

        public Anime(String title, String author, int year) {
            this.title = title;
            this.author = author;
            this.year = year;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getYear() {
            return year;
        }

        public String toString() {
            return (getTitle() + " " + getAuthor() + " " + getYear());
        }
    }
}
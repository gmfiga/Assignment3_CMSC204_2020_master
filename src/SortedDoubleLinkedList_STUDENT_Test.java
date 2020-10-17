import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SortedDoubleLinkedList_STUDENT_Test {

    SortedDoubleLinkedList<Anime> sortedLinkedAnime;
    AnimeComparator comparatorAnime;

    public Anime a = new Anime("Fairy Tail", "Hiro Mashima", 2006);
    public Anime b = new Anime("Jo Jo's Bizarre Adventure", "Hirohiko Araki", 1987);
    public Anime c = new Anime("Hunter x Hunter", "Yoshihiro Togashi", 1998 );
    public Anime d = new Anime("Sword Art Online", "Reki Kawahara", 2002 );
    public Anime e = new Anime("Captain Tsubasa", "Yoichi Takahashi", 1981 );
    public Anime f = new Anime("Chihiro", "Hayao Miyazaki", 2001);
    //alphabetic order: e f a c b d

    @Before
    public void setUp() throws Exception {

        comparatorAnime = new AnimeComparator();
        sortedLinkedAnime = new SortedDoubleLinkedList<Anime>(comparatorAnime);

    }

    @After
    public void tearDown() throws Exception {

        comparatorAnime = null;
        sortedLinkedAnime = null;
    }

    @Test
    public void testAddToEnd() {
        try {
            sortedLinkedAnime.addToEnd(a);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddToFront() {
        try {
            sortedLinkedAnime.addToFront(a);
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testIteratorSuccessfulNext() {
        sortedLinkedAnime.add(a);
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(c);
        sortedLinkedAnime.add(d);
        ListIterator<Anime> iterator = sortedLinkedAnime.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(a, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulCarPrevious() {
        sortedLinkedAnime.add(e);
        sortedLinkedAnime.add(c);
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(d);
        ListIterator<Anime> iterator = sortedLinkedAnime.iterator();
        assertEquals(true, iterator.hasNext());
        assertEquals(e, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(d, iterator.next());
        assertEquals(true, iterator.hasPrevious());
        assertEquals(d, iterator.previous());
        assertEquals(b, iterator.previous());
        assertEquals(c, iterator.previous());
    }


    @Test
    public void testIteratorNoSuchElementException() {
        sortedLinkedAnime.add(e);
        sortedLinkedAnime.add(c);
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(d);
        ListIterator<Anime> iterator = sortedLinkedAnime.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(e, iterator.next());
        assertEquals(c, iterator.next());
        assertEquals(b, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(d, iterator.next());
        try {
            //no more elements in list
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationExceptionString() {
        sortedLinkedAnime.add(e);
        sortedLinkedAnime.add(c);
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(d);
        ListIterator<Anime> iterator = sortedLinkedAnime.iterator();

        try {
            //remove is not supported for the iterator
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddAnime() {
        sortedLinkedAnime.add(a);
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(c);
        assertEquals(a, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        sortedLinkedAnime.add(d);
        sortedLinkedAnime.add(e);
        assertEquals(e, sortedLinkedAnime.getFirst());
        assertEquals(d, sortedLinkedAnime.getLast());
        //deletes Elephant from linked list
        assertEquals(d, sortedLinkedAnime.retrieveLastElement());
        assertEquals(b, sortedLinkedAnime.getLast());
    }

    @Test
    public void testRemoveFirstAnime() {
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(c);
        assertEquals(c, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        sortedLinkedAnime.add(a);
        assertEquals(a, sortedLinkedAnime.getFirst());
        sortedLinkedAnime.remove(a, comparatorAnime);
        assertEquals(c, sortedLinkedAnime.getFirst());
    }

    @Test
    public void testRemoveEndAnime() {
        sortedLinkedAnime.add(b);
        sortedLinkedAnime.add(f);
        assertEquals(f, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        sortedLinkedAnime.add(d);
        assertEquals(d, sortedLinkedAnime.getLast());
        sortedLinkedAnime.remove(d, comparatorAnime);
        assertEquals(b, sortedLinkedAnime.getLast());
    }

    @Test
    public void testRemoveMiddleAnime() {
        sortedLinkedAnime.add(a);
        sortedLinkedAnime.add(b);
        assertEquals(a, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        sortedLinkedAnime.add(f);
        assertEquals(f, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        assertEquals(3, sortedLinkedAnime.getSize());
        sortedLinkedAnime.remove(a, comparatorAnime);
        assertEquals(f, sortedLinkedAnime.getFirst());
        assertEquals(b, sortedLinkedAnime.getLast());
        assertEquals(2, sortedLinkedAnime.getSize());
    }


    private class AnimeComparator implements Comparator<Anime> {

        @Override
        public int compare(Anime arg0, Anime arg1) {
            return arg0.getTitle().compareTo(arg1.getTitle());
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
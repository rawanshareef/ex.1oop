package ex1.testes;

import ex1.src.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

class WGraph_DSTest {
    
	 @Test
	    void getNode() {
	        weighted_graph g = new WGraph_DS();
    g.addNode(5);
    g.addNode(3);
    g.addNode(9);
    node_info a=g.getNode(8);
    assertEquals(null,a);
	 }
	
	
	
    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        int n = g.nodeSize();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(2);
        g.addNode(4);
        g.addNode(9);
        g.addNode(13);
        g.removeNode(0);
        g.removeNode(13);
        g.addNode(3);
        g.removeNode(9);
        n = g.nodeSize();
        assertEquals(4,n);
        g.addNode(7);
        g.addNode(17);
        g.addNode(29);
        g.removeNode(29);
        n = g.nodeSize();
        assertEquals(6,n);

    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(7);
        g.connect(7,20,90);
        g.connect(1,4,2);
        g.connect(1,3,1);
        g.connect(1,2,5);
        g.connect(0,2,5);
        g.removeNode(7);
        g.connect(1,4,-1);
        int e_size =  g.edgeSize();
        assertEquals(3, e_size);
        double w03 = g.getEdge(1,3);
        double w30 = g.getEdge(3,1);
        assertEquals(w03, w30);
        assertEquals(w03, 1);
        double w14= g.getEdge(1,4);
        double w41= g.getEdge(4,1);
        assertEquals(w14, w41);
        assertEquals(w14, 2);

    }

    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(9);
        g.connect(1,9,7);
        g.connect(0,1,1);
        g.connect(0,2,2);
        g.connect(0,2,4);
        g.connect(0,3,3);
        g.connect(0,1,1);
        Collection<node_info> t = g.getV();
        Iterator<node_info> it= t.iterator();
        while (it.hasNext()) {
            node_info n = it.next();
            assertNotNull(n);
        }

    }

    @Test
    void hasEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(7);
        g.connect(1, 2, 5);
        boolean flage1=g.hasEdge(1, 2);
        assertEquals(true,flage1);
        g.connect(1, 0, 5);
        boolean flage2=g.hasEdge(1, 0);
        assertEquals(false,flage2);
        g.removeNode(1);
        boolean flage3=g.hasEdge(1, 2);
        assertEquals(false,flage3);
        
    }

    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();
        g.addNode(1);
        g.addNode(7);
        g.addNode(14);
        g.addNode(5);
        g.connect(1,14,1);
        g.connect(1,7,3);
        g.connect(1,5,3);
        g.removeEdge(1,14);
        g.removeEdge(9,14);
        assertFalse(g.hasEdge(1,14));
        g.removeEdge(2,1);
        g.connect(1,14,1);
        double f = g.getEdge(1,14);
        assertEquals(f,1);
        g.removeEdge(1,7);
        assertFalse(g.hasEdge(1,7));
        assertTrue(g.hasEdge(1,5));
        g.connect(1,5,5);
        double h = g.getEdge(1,5);
        assertEquals(h,5);

    }


    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        g.addNode(4);
        g.addNode(2);
        g.addNode(15);
        g.addNode(3);
        g.connect(4,2,1);
        g.connect(4,15,1);
        g.connect(4,3,3);
        g.connect(4,5,1);
        g.removeNode(4);
        g.removeNode(9);
        assertFalse(g.hasEdge(4,2));
        int ed= g.edgeSize();
        assertEquals(0,ed);
        assertEquals(3,g.nodeSize());
        assertFalse(g.hasEdge(4, 15));
        g.connect(4,15,1);
        assertFalse(g.hasEdge(4,15));
    }

    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        g.addNode(7);
        g.addNode(4);
        g.addNode(5);
        g.addNode(3);
        g.connect(7,4,8);
        g.connect(7,5,2);
        g.connect(7,3,2);
        g.removeEdge(7,3);
        g.removeEdge(7,7);
        double wi= g.getEdge(7,3);
        assertEquals(wi,-1);

    }
    @Test
    void mc() {
        weighted_graph g = new WGraph_DS();
        double Mc=g.getMC();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(2);
        Mc=g.getMC();
        assertEquals(3,Mc);
        
    }
}

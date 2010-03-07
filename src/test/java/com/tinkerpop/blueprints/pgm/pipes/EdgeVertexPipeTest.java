package com.tinkerpop.blueprints.pgm.pipes;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory;
import junit.framework.TestCase;

/**
 * @author: Marko A. Rodriguez (http://markorodriguez.com)
 */
public class EdgeVertexPipeTest extends TestCase {

    public void testInCommingVertex() {
        Graph graph = TinkerGraphFactory.createTinkerGraph();
        Vertex marko = graph.getVertex("1");
        EdgeVertexPipe evp = new EdgeVertexPipe(EdgeVertexPipe.Step.IN_VERTEX);
        evp.setStarts(marko.getOutEdges().iterator());
        assertTrue(evp.hasNext());
        int counter = 0;
        while (evp.hasNext()) {
            Vertex v = evp.next();
            assertTrue(v.getId().equals("2") || v.getId().equals("3") || v.getId().equals("4"));
            counter++;
        }
        assertEquals(counter, 3);

        Vertex josh = graph.getVertex("4");
        evp = new EdgeVertexPipe(EdgeVertexPipe.Step.IN_VERTEX);
        evp.setStarts(josh.getOutEdges().iterator());
        assertTrue(evp.hasNext());
        counter = 0;
        while (evp.hasNext()) {
            Vertex v = evp.next();
            assertTrue(v.getId().equals("5") || v.getId().equals("3"));
            counter++;
        }
        assertEquals(counter, 2);
    }
}
package mathtree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class MathParserTest extends TestCase {

//    public void testIsparsed() throws Exception {
//        MathParser p = new MathParser("2+3");
//        assertEquals(1,p.isparsed());
//    }

    public void testrawprint() throws Exception{
        String[] elements = "46+93/*".split("");
        List<MathTreeNode> tree = new ArrayList<MathTreeNode>();
        for (String elem : elements)
            tree.add(new MathTreeNode(elem));
        MathTree mt = new MathTree(tree);
        assertEquals("46+93/*",mt.printraw());
    }

    public void test() throws Exception{
        List<MathTreeNode> tree = new ArrayList<MathTreeNode>();
        tree.add(new MathTreeNode("+"));
        MathTreeNode minus = new MathTreeNode("-");
        tree.add(minus);
        assertEquals(1, tree.indexOf(minus));
        assertEquals("+", tree.get(tree.size()-2).name);
    }

    public void testWrap() throws Exception{
        String[] elements = "46+93/*".split("");
        List<MathTreeNode> tree = new ArrayList<MathTreeNode>();
        for (String elem : elements)
            tree.add(new MathTreeNode(elem));
        MathTree mt = new MathTree(tree);
        List<MathTreeNode> n = mt.wrap();
        MathTree new_mt = new MathTree(n);
        List<MathTreeNode> nn = new_mt.wrap();
        MathTree new_nmt = new MathTree(nn);
        assertEquals("+/*", new_mt.printraw());
        assertEquals("*", new_nmt.printraw());
    }

    public void testMathparse() throws Exception{
        MathParser p = new MathParser("4*6+2/3");
        MathParser p2 = new MathParser("6+3*4/3");
        MathParser p3 = new MathParser("(6+3)*4/3");
        MathParser p4 = new MathParser("((6+3)*4)/3");

        assertEquals("46*23/+", p.parse());
        assertEquals("634*3/+", p2.parse());
        assertEquals("63+4*3/", p3.parse());
        assertEquals("63+4*3/", p4.parse());
    }

    public void testMatchScope() throws Exception{
        String scope = "(";
        assertEquals(true,scope.matches("\\("));
    }
}
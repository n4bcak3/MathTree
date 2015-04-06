package mathtree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class MathTreeNodeTest extends TestCase {

    public void testIsState() throws Exception {
        MathTreeNode op = new MathTreeNode("+");
        MathTreeNode op1 = new MathTreeNode("-");
        MathTreeNode op2 = new MathTreeNode("/");
        MathTreeNode op3 = new MathTreeNode("*");
        assertTrue(op.isState());
        assertTrue(op1.isState());
        assertTrue(op2.isState());
        assertTrue(op3.isState());
    }

    public void testIsStateFalse() throws Exception{
        MathTreeNode numb = new MathTreeNode("8");
        assertFalse(numb.isState());
    }
    public void testNumbornode() throws Exception{
        List<MathTreeNode> childs = new ArrayList<MathTreeNode>();
        childs.add(new MathTreeNode("3"));
        childs.add(new MathTreeNode("2"));
        MathTreeNode i = new MathTreeNode("7");

        MathTreeNode node = new MathTreeNode("/",childs);
        assertTrue(node.numbornode());
        assertTrue(i.numbornode());
    }
}
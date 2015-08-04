package algorithm;

/**
 * Created by xzbang on 2015/7/13.
 */
enum Color{
    RED,BLACK;
}

class RBTreeNode{
    private final static RBTreeNode NIL = new RBTreeNode(0);

    public RBTreeNode(int value){
        this.value=value;
    }

    RBTreeNode left=NIL,right=NIL,parent=NIL;
    int value = 0;
    Color color = Color.BLACK;

    public static RBTreeNode getNIL(){
        return NIL;
    }
}

public class RBTree {
    private RBTreeNode root,nil;
    public RBTree(RBTreeNode root){
        this.root = root;
        nil = RBTreeNode.getNIL();
    }
    private void leftRotate(RBTreeNode x){
        RBTreeNode y = x.right;
        if(y==nil)return;
        x.right = y.left;
        y.parent = x.parent;
        if(y.left!=nil)y.left.parent=x;
        if(x.parent==nil)root=y;
        else if(x==x.parent.left)x.parent.left=y;
        else x.parent.right=y;
        y.left=x;
        x.parent=y;
    }

    private void rightRotate(RBTreeNode x){
        RBTreeNode y = x.left;
        if(y==nil)return;
        x.left=y.right;
        y.parent=x.parent;
        if(y.right!=nil)y.right.parent=x;
        if(x.parent==nil)root=y;
        else if(x==x.parent.left)x.parent.left = y;
        else x.parent.right = y;
        y.right=x;
        x.parent=y;
    }

    public void insert(RBTreeNode x){
        RBTreeNode y = root,z=nil;
        while(y!=nil){
            z=y;
            if(x.value<y.value)y=y.left;
            else y=y.right;
        }

        if(z==nil)root=x;
        else if(x.value<z.value)z.left=x;
        else z.right=x;
        x.parent=z;
        x.color=Color.RED;
        insertFixup(x);
    }

    private void insertFixup(RBTreeNode x) {
        while(x.parent.color==Color.RED){
            if(x.parent==x.parent.parent.left){
                RBTreeNode y = x.parent.parent.right;
                if(y.color==Color.RED){
                    x.parent.color=Color.BLACK;
                    y.color=Color.BLACK;
                    x.parent.parent.color=Color.RED;
                    x=x.parent.parent;
                }else if(x==x.parent.right){
                    x=x.parent;
                    leftRotate(x);
                }
                x.parent.color=Color.BLACK;
                x.parent.parent.color=Color.RED;
                rightRotate(x.parent.parent);
            }else{
                RBTreeNode y = x.parent.parent.left;
                if(y.color==Color.RED){
                    x.parent.color=Color.BLACK;
                    y.color=Color.BLACK;
                    x.parent.parent.color=Color.RED;
                    x=x.parent.parent;
                }else if(x==x.parent.left){
                    x=x.parent;
                    rightRotate(x);
                }
                x.parent.color=Color.BLACK;
                x.parent.parent.color=Color.RED;
                leftRotate(x.parent.parent);
            }
        }
        root.color=Color.BLACK;
    }

    public void delete(RBTreeNode z){
        RBTreeNode y = z,x;
        Color trueDeleteColor = y.color;
        if(z.left==nil){
            x=z.right;
            transplant(z,z.right);
        }else if(z.right==nil){
            x=z.left;
            transplant(z,z.left);
        } else{
            y=RBTreeMinmum(z.right);
            trueDeleteColor=y.color;
            x=y.right;
            if(y.parent==z)x.parent=y;
            else{
                transplant(y,y.right);
                y.right=z.right;
                y.right.parent=y;
            }
            transplant(z,y);
            y.left=z;
            y.left.parent=y;
            y.color=z.color;
        }
        if(trueDeleteColor==Color.BLACK)deleteFixup(x);
    }

    private void deleteFixup(RBTreeNode x) {
        while(x.parent!=nil&&x.color==Color.BLACK){
            if(x==x.parent.left){
                RBTreeNode y=x.parent.right;
                if(y.color==Color.RED){
                    leftRotate(x.parent);
                    y.color=Color.BLACK;
                    y=x.parent.right;
                    x.parent.color=Color.RED;
                }
                if(y.left.color==Color.BLACK && y.right.color==Color.BLACK){
                    y.color=Color.RED;
                    x=x.parent;
                    continue;
                }else if(y.right.color==Color.BLACK){
                    y.left.color=Color.BLACK;
                    y.color=Color.RED;
                    rightRotate(y);
                    y=x.parent.right;
                }
                y.color=x.parent.color;
                x.parent.color=Color.BLACK;
                y.right.color=Color.BLACK;
                leftRotate(x.parent);
                x=root;
            }else{
                RBTreeNode y = x.parent.right;
                if(y.color==Color.RED){
                    rightRotate(x.parent);
                    y.color=Color.BLACK;
                    y=x.parent.right;
                    x.parent.color=Color.RED;
                }
                if(y.left.color==Color.BLACK && y.right.color==Color.BLACK) {
                    y.color = Color.RED;
                    x = x.parent;
                    continue;
                }else if(y.left.color==Color.BLACK){
                    y.right.color=Color.BLACK;
                    y.color=Color.RED;
                    leftRotate(y);
                    y=x.parent.left;
                }
                y.color=x.parent.color;
                x.parent.color=Color.BLACK;
                y.left.color=Color.BLACK;
                rightRotate(x.parent);
                x=root;
            }
        }
        x.color=Color.BLACK;
    }

    private RBTreeNode RBTreeMinmum(RBTreeNode x) {
        while(x.left!=nil)x=x.left;
        return x;
    }

    private void transplant(RBTreeNode x, RBTreeNode y) {
        if(x.parent==nil) root=y;
        if(x==x.parent.left)y=x.parent.left;
        if(x==x.parent.right)y=x.parent.right;
        y.parent=x.parent;//这个地方可能给nil.parent赋值，除了方便计算，不影响其他
    }
}

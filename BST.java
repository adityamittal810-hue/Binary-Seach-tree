import java.util.*;
public class BST {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data)
        {
            this.data=data;
        }
    }

    //Building a tree 
    public static Node insert(Node root,int val)
    {
        if(root==null)
        {
            root = new Node(val);
            return root;
        }
        //for right subtree
        if(root.data<val)
        {
            root.right=insert(root.right,val);
        }
        else{
            root.left=insert(root.left,val);
        }
        return root;
    }

    //To check the tree is build is properly or not:-
    //iorder traversal print the bst in sequence wise
    public static void inorder(Node root)
    {
        if(root==null)
        {
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    //To search:-
    public static boolean search(Node root,int key)
    {
        if(root==null)
        {
            return false;
        }
        if(root.data>key)
        {
            return search(root.left,key);
        }
        else if(root.data<key)
        {
            return search(root.right,key);
        }
        else
        {//when(root==key)
            return true;
        }
    }

    //to delete the node :-
    public static Node delete(Node root, int val)
    {//Always remeber:- when we update the tree by any operation like insertion or deletetion we have
    //  to reconnect the nodes of our tree so we write "root.left= insert/delte(root.left)"
        if(root==null)
        {
            return null;
        }
        if(root.data>val)
        {
            root.left=delete(root.left,val);
        }
        else if(root.data<val)
        {
            root.right = delete(root.right,val);
        }
        else
        {//Here we find our node
            //case:1 when the node is the leaf node 
            // (int that case we can directly delete that node)
            if(root.left==null&&root.right==null)
            {
                return null;
            }
            
            // case:2 when the node has only one child node:-
            // (in that case we delete that node and place his child node to it's parent position)
            if(root.right==null)
            {
                return root.left;
            }
            else if(root.left==null)
            {
                return root.right;
            }

            //case:3 when node has two children
            //In this case we find the inorder succesor pf that node and place that node to the
            //delted node(inorder suiccesor:- it is left most ndoe in the right subtree)

            Node IS = inordersucsessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right,IS.data);
        }
        return root;
    }
    public static Node inordersucsessor(Node root)
    {
        while(root.left!=null)
        {
            root=root.left;
        }
        return root;
    }

    //To print in range:-
    public static void PrintinRange(Node root,int x,int y)
    {
        if(root==null)
        {
            return ;
        }
        if(root.data>=x&&root.data<=y)
        {
            PrintinRange(root.left,x,y);
            System.out.print(root.data+" ");
            PrintinRange(root.right,x,y);
            return;
        }
        else if(root.data<x)
        {
            PrintinRange(root.right,x,y);
        }
        else
        {//(root.data>y)
            PrintinRange(root.left,x,y);
        }
    }

    //Print path from root to leaf:-
    public static void Printroottoleaf(Node root,ArrayList<Integer> path)
    {
        if(root==null)
        {
            return;
        }
        path.add(root.data);
        if(root.left==null&&root.right==null)
        {
            PrintPath(path);
        }
        else{
            Printroottoleaf(root.left,path);
            Printroottoleaf(root.right,path);
        }
        path.remove(path.size()-1);
    }
    public static void PrintPath(ArrayList<Integer> path)
    {
        for(int i=0;i<path.size();i++)
            {
                System.out.print(path.get(i)+"->");
            }
            System.out.println("END");
    }

    public static void main(String[] args) {
        int[] val = {5,1,3,4,2,7};
        Node root =  null;
        for(int i=0;i<val.length;i++)
        {
            root = insert(root,val[i]);
        }

        // To check:-printing the tree in inorder traversal
        inorder(root);
        System.out.println();

        // //to find a node in a tree:-
        // if(search(root,1))
        // {
        //     System.out.println("Found");
        // }else{
        //     System.out.println("Not found");
        // }

        // //To delete:-
        // delete(root,5);
        // inorder(root);

        //PrintinRange:-
        // PrintinRange(root,1,4);

        //Print root to leaf:-
        Printroottoleaf(root, new ArrayList<>());
    }
}

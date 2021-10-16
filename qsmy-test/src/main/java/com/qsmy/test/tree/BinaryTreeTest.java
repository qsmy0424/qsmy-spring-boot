package com.qsmy.test.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author qsmy
 * @time 2021/10/16
 */
public class BinaryTreeTest {

    public static BinaryTree insert(BinaryTree root, Integer data) {
        if (root == null) {
            return new BinaryTree(data);
        } else {
            BinaryTree cur;
            if (data <= root.value) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void preOrder(BinaryTree root) {
        if (root != null) {
            System.out.println(root.value);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void preOrder2(BinaryTree root) {
        BinaryTree cur;
        Deque<BinaryTree> deque = new LinkedList<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            cur = deque.pop();
            System.out.println(cur.value);
            if (cur.right != null) {
                deque.push(cur.right);
            }
            if (cur.left != null) {
                deque.push(cur.left);
            }
        }

    }


    public static void inOrder(BinaryTree root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.value);
            inOrder(root.right);
        }
    }

    public static void inOrder2(BinaryTree root) {
        BinaryTree cur = root;
        Deque<BinaryTree> deque = new LinkedList<>();

        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.push(cur);
                cur = cur.left;
            }

            cur = deque.pop();
            System.out.println(cur.value);
            cur = cur.right;
        }

    }

    public static void postOrder(BinaryTree root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value);
        }
    }

    public static void postOrder2(BinaryTree root) {
        BinaryTree cur = root;
        BinaryTree right = null;
        Deque<BinaryTree> deque = new LinkedList<>();
        while (cur != null || !deque.isEmpty()) {
            while (cur != null) {
                deque.push(cur);
                cur = cur.left;
            }

            cur = deque.pop();
            while (cur.right == null || cur.right == right) {
                System.out.println(cur.value);
                right = cur;
                if (deque.isEmpty()) {
                    return;
                }
                cur = deque.pop();
            }

            deque.push(cur);
            cur = cur.right;

        }
    }

    public static void levelOrder(BinaryTree root) {
        BinaryTree cur;
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(35);
        insert(root, 20);
        insert(root, 40);
        insert(root, 15);
        insert(root, 16);
        insert(root, 29);
        insert(root, 28);
        insert(root, 30);
        insert(root, 50);
        insert(root, 45);
        insert(root, 55);
        System.out.println(root);
//        preOrder(root);
//        inOrder(root);
//        postOrder(root);
//        preOrder2(root);
//        inOrder2(root);
//        postOrder2(root);
        levelOrder(root);

    }

}

class BinaryTree {
    Integer value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(Integer value) {
        this.value = value;
        left = right = null;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
# -*- coding: utf-8 -*-
# @Author   : ZhengHj
# @Time     : 2019/5/8 22:21
# @Project  : testProject
# @File     : tree.py
# @Software : PyCharm


class TreeNode:
    """ Definition for a binary tree node. """
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class BinaryTree:

    @classmethod
    def build_tree(cls, lst: list):
        """ 非递归方法，用列表创建二叉树，层序创建 """
        if not lst:
            return
        root = TreeNode(lst[0])
        # 用队列queue保存上一层的节点，用tmp保存新添加的子节点
        queue = [root]
        tmp = []
        # 用rexits表示右子节点是否已添加
        rexits = True
        for i in lst[1:]:
            # 如果当前节点的右子节点已添加，需要从queue中取一个节点
            if rexits:
                # 如果queue为空，表示这层的节点的子节点已全部添加完，另queue=tmp, tmp=[]，开始下一层
                if not queue:
                    queue = tmp
                    tmp = []
                node = queue.pop(0)
                # 新取的节点显然rexits=False，将i添加到node的左子节点上
                rexits = False
                # 如果取出来的node是空，此时无法添加新子节点，所以continue
                if not node:
                    continue
                # 添加左子节点，如果i不存在，不添加左子节点，但是tmp也要保存一个None用来占位
                if i:
                    node.left = TreeNode(i)
                    tmp.append(node.left)
                else:
                    tmp.append(None)
            # 添加右子节点，将rexits设为False，其余同添加左子节点
            elif not rexits:
                rexits = True
                if i:
                    node.right = TreeNode(i)
                    tmp.append(node.right)
                else:
                    tmp.append(None)
        return root

    @classmethod
    def preorder_traversal(cls, root: TreeNode):
        """ 递归，前序遍历，root - left - right的顺序 """
        res = []
        cls._preorder_traversal(root, res)
        return res

    @classmethod
    def _preorder_traversal(cls, root: TreeNode, res: list):
        if not root:
            return
        res.append(root.val)
        cls._preorder_traversal(root.left, res)
        cls._preorder_traversal(root.right, res)

    @classmethod
    def inorder_traversal(cls, root: TreeNode):
        """ 递归，中序遍历，left - root - right的顺序 """
        res = []
        cls._inorder_traversal(root, res)
        return res

    @classmethod
    def _inorder_traversal(cls, root: TreeNode, res: list):
        if not root:
            return
        cls._inorder_traversal(root.left, res)
        res.append(root.val)
        cls._inorder_traversal(root.right, res)

    @classmethod
    def postorder_traversal(cls, root: TreeNode):
        """ 递归，后序遍历，left - right - root的顺序 """
        res = []
        cls._postorder_traversal(root, res)
        return res

    @classmethod
    def _postorder_traversal(cls, root: TreeNode, res: list):
        if not root:
            return
        cls._postorder_traversal(root.left, res)
        cls._postorder_traversal(root.right, res)
        res.append(root.val)

    @classmethod
    def invertTree(cls, root: TreeNode) -> TreeNode:
        """ 翻转二叉树 """
        if not root:
            return root
        left = cls.invertTree(root.right)
        right = cls.invertTree(root.left)
        root.right = right
        root.left = left
        return root

    @classmethod
    def isSameTree(cls, p: TreeNode, q: TreeNode) -> bool:
        """ 判断两棵树是否相同 """
        if p and q and p.val == q.val:
            return cls.isSameTree(p.left, q.left) and cls.isSameTree(p.right, q.right)
        elif p or q:
            return False
        else:
            return True

    @classmethod
    def binaryTreePaths(cls, root: TreeNode):
        """ 返回所有从根节点到叶子节点的路径 """
        stack, paths = [], []
        return cls._binaryTreePaths(root, stack, paths)

    def _binaryTreePaths(cls, root: TreeNode, stack, paths):
        if not root:
            return paths
        stack.append(root)
        if not root.left and not root.right:
            paths.append("->".join(str(x.val) for x in stack))
        else:
            cls._binaryTreePaths(root.left, stack, paths)
            cls._binaryTreePaths(root.right, stack, paths)
        stack.pop()
        return paths



"""
            1
        2       3
     4   5    N    6
   N   N N N N N  7
"""

root = BinaryTree.build_tree([1, 2, 3, 4, 5, None, 6, None, None, None, None, None, None, 7])
print(BinaryTree.preorder_traversal(root))
print(BinaryTree.inorder_traversal(root))
print(BinaryTree.postorder_traversal(root))
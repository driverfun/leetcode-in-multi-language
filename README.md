# Leetcode In Multi-language

## 前言

**我们的口号是：面向跳槽刷题！！！**

既然这个仓库被闲置了，我就拿它做刷题打卡地了。其实写得好的题解仓库有很多，github搜`leetcode`能找到一堆（详见后文），但真正能发挥功效的唯有自己的坚持不懈。

## 阅读资源

一些实用的github项目（题解、面试），整理如下：

- [题解的动画版](https://github.com/MisterBooo/LeetCodeAnimation)
- [labuladong的题解版](https://github.com/labuladong/fucking-algorithm)
- [后端常见面试题](https://github.com/yuanguangxin/LeetCode/blob/master/Rocket.md)

电子书《labuladong的算法小抄》
```bash
链接: https://pan.baidu.com/s/1WH9IgfZoLXj0KT0Sm_dWyw 提取码: 652e
```

刷题网站：[leetcode合作中文网站](https://leetcode-cn.com/) 

安利指数：:star::star::star::star::star:，每道题有视频讲解，很清楚很省时。

## 建议

每道题最好记录下自己最初的思路，有些做不出的题目在看了题解后再回头比较，这样反思收获的更多吧。

## 【llf-path】

| 题目序号 | 题目名称 | 难度 | 通过与否 | 是否多解 | 优化空间（越小代表越优） |
| -- | -- | -- | -- | -- | -- |
| 124 | 二叉树中的最大路径和 | hard | 否 | 2 | |
| 105 | 从前序与中序遍历序列构造二叉树 | medium | 是 | | 10% |
| 99 |  恢复二叉搜索树 | hard | 是 | 3 | 40% |
| 94 | 二叉树的中序遍历 | medium | 是 | 3（Morris遍历） | 0% |
| 144 | 二叉树的前序遍历 | medium | 是 | 3 | 0% |
| 145 | 二叉树的后序遍历 | medium | 是 | 3 | 0% |
| 102 | 二叉树的层序遍历 | medium | 是 |  | 0% |
| 103 | 二叉树的锯齿形层次遍历 | medium | 是 | 2 | 20% | 
| 106 | 中序与后序遍历序列构造二叉树 | medium | 是 |  | 10% | 
| 112 | 路径总和 | easy | 是 | 2 | 0% |
| 113 | 路径总和 II | medium | 是 | 2 | 0% |
| 96 | 不同的二叉搜索树 | medium | 是 | 2 | 0% |
| 98 | 验证二叉搜索树 | medium | 是 | 2 | 0% | 
| 114 | 二叉树展开为链表 | medium | 是 | 2 | 30% | 
| 116 | 填充每个节点的下一个右侧节点指针 | medium | 是 | 3 | 40% |
| 129 | 求根到叶子节点数字之和 | medium | 是 |  | 5% |

## ChangeLog

[2020-12-14] | 
1. 将所有的题解代码转入`./solutions`文件夹
2. 编写统一接口`SolutionsFacade`，以后所有解题代码都要实现它
3. 利用反射机制创建测试实例，避免大量`import xxx`和重复写好多测试数据，后来想想似乎有点多余
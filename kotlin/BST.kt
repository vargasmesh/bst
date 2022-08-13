class BST (var value: Int) {
    var left: BST? = null
    var right: BST? = null

    fun insert(v: Int): BST {
        if (v > this.value) this.right?.insert(v) ?: run {
            this.right = BST(v)
        }

        if (v < this.value) this.left?.insert(v) ?: run {
            this.left = BST(v)
        }

        return this
    }

    fun delete(key: Int): BST? {
        if (this.value == key) {
            // the two lines below cover both single nodes and nodes with only one child
            if (this.left == null) return this.right
            if (this.right == null) return this.left

            /*
            Here we know that the node to be removed has two children.
            Therefore, we must swap this node value with the lowest value from the right subtree
            */
            this.right?.let {
                var minNode = it
                while (minNode.left != null) minNode.left?.let {minNode = it}
                val temp = this.value
                this.value = minNode.value
                minNode.value = temp
                right = it.delete(key)
            }
        }

        if (this.value > key) this.left = this.left?.delete(key)
        else this.right = this.right?.delete(key)

        /*
        we return the same node because either we had found the value and swapped
        or the key does not exist in this BST
        */
        return this
    }
}



fun inorder(bst: BST?): Unit {
    if (bst != null) {
        inorder(bst.left)
        print("${bst.value} ")
        inorder(bst.right)
    }
}


fun main() {
    var myBST = BST(10)

    myBST.insert(2)
        .insert(11)
        .insert(3)
        .insert(4)
        .insert(5)
        .insert(6)
        .insert(7)

    inorder(myBST)

    myBST.delete(4)

    println()
    inorder(myBST)
}

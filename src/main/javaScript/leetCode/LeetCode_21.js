// You are given the heads of two sorted linked lists list1 and list2.
// Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
// Return the head of the merged linked list.

// function mounted() {
//
// }
//
// mounted()
// {
//     this.window = window;
// }

function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val)
    this.next = (next === undefined ? null : next)
}

var mergeTwoLists = function (list1, list2) {
    var leftNode = list1, rightNode = list2;

    var head = new ListNode(), allHead = head;

    while (leftNode != null && rightNode != null) {

        if (leftNode.val < rightNode.val) {

            head.next = leftNode;

            leftNode = leftNode.next;
        } else {
            head.next = rightNode;

            rightNode = rightNode.next;
        }

        head = head.next;
    }

    while (leftNode != null) {

        head.next = leftNode;

        leftNode = leftNode.next;

        head = head.next;
    }

    while (rightNode != null) {

        head.next = rightNode;

        rightNode = rightNode.next;

        head = head.next;
    }

    return allHead.next;
};

function fn() {
    window.console.log("fn function.")
}

// void main()
// {
//     fn();
// }
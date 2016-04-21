# Lib: Phip Linked Lists for Java
Hi folks,
This project is a implementation of various linked lists in Java.
Mainly I made this to practice but feel free to contribute or just to learn
from the code I've written. Actually I became a big fan of my BidirectionalList
implementation because it offers a lot of functionality and many methods
such as pop() (to use it as a stack), several delete-methods and many more!
SimpleList is just a demonstration, you should never use it because it
is really slow. Have fun and let me know what you think: Suggestions or
questions please to Philipp.Schuster@phip1611.de

For a very simple case where you can use one of my lists for example
have a look at this: https://gist.github.com/phip1611/bbb4e461d57bcbee52c21900e753b21a

Full feature List of BidirectionalList (the List that you should use!)
- append items
- insert items
- delete(value): deletes the first occurrence of the element in the List
- deleteAll(value): deletes all occurrences of the element in the List
- deleteAt(index) deletes the element at the index
- pop() returns the last element and deletes it (you can use it like it is a stack)
- getValue(index)
- getIndex(value)
- getIndexes(value) if there is a element multiple times in the list you get an array with all indexes
- isInList(value)
- getLast() like pop() but doesn't delete the last element
- printList()/printListBackwards(): perhaps interesting for debugging reasons (with small lists)
- clear() wipes the list

- you can iterate through the list with a foreach loop!
- you can set strict mode true so that you will not be able to add several items more than once
- (maybe even some more, not quite sure yet haha!)

I hope you like my work. Feel free to test and use it and have fun.


Class Hierarchy: List (abstract) > LinearList (abstract) > SimpleList, BidirectionalList

Class Hierarchy:  ListElement (abstract) > LinearListElement > BidirectionalListElement

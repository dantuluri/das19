template <typename T>
class Node
{
public:
	T data;
	Node* next;
	Node() { next = NULL; data = 0; }
	Node(int d) { next = NULL; data = d; }
};
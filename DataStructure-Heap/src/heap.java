
public class heap {
	
	private Node root;
	private Node lastnode;
	private Node lastnodeParents;
	private boolean lastnodeRight = false;
	private boolean lastnodeLeft = false;
	
	private class Node{
		
		private int key;
		private Node right;
		private Node left;
		private Node parents;
		
		Node(int key,Node parents){
			this.key = key;
			this.parents = parents;
			right = null;
			left = null;
		}
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void insert(int key){
		if (isEmpty()){
			root = new Node(key,null);
			lastnode = root;
		}
		else {
			lastnode = FindLastnode(lastnode);
			lastnode = new Node(key,lastnodeParents);
			if (lastnodeLeft){
				lastnodeParents.left = lastnode;
			}
			else if (lastnodeRight){
				lastnodeParents.right = lastnode;
			}
		}
	}
	
	public Node FindLastnode(Node lastnode){
		if (lastnode == root){
			// ########################## //
			lastnodeParents = root;
			lastnodeLeft = true;
			lastnodeRight = false;
			// ########################## //
			return lastnode.left;
		}
		else {
			if (lastnode == lastnode.parents.left){
				// ########################## //
				lastnodeParents = lastnode.parents;
				lastnodeLeft = false;
				lastnodeRight = true;
				// ########################## //
				return lastnode.parents.right;
			}
			else {
				Node temp = lastnode.parents;
				while (temp != root){
					if (temp == temp.parents.left){
						temp = temp.parents.right;
						break;
					}
					else {
						temp = temp.parents;
					}
				}
				while (temp != null){
					// ########################## //
					lastnodeParents = temp;
					// ########################## //
					temp = temp.left;
				}
				// ########################## //
				lastnodeLeft = true;
				lastnodeRight = false;
				// ########################## //
				return temp;
			}
		}
	}
	
	public void print_preorder(){
		System.out.print("Preorder : ");
		print_preorder(root);
		System.out.println();
	}
	
	private void print_preorder(Node temp){
		if (temp != null){
			System.out.print(temp.key+" ");
			print_preorder(temp.left);
			print_preorder(temp.right);
		}
	}

}

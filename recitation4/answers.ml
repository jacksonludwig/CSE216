(* Q1 *)
type 'a binary_tree =
  | Empty
  | Node of 'a * 'a binary_tree * 'a binary_tree

let rec count_leaves tree =
  match tree with
  | Empty -> 0
  | Node(_, Empty, Empty) -> 1
  | Node(_, left, right) -> count_leaves left + count_leaves right


(* Q2 *)
type ('a,'b) tree =
  | Empty
  | Leaf of 'a
  | Tree of ('a,'b) node
and
  ('a,'b) node = {
  operator: 'b;
  left: ('a,'b) tree;
  right: ('a,'b) tree;
}


(* Q3 *)
let rec nodes_and_leaves tr =
  match tr with
  | Empty -> ([], [])
  | Leaf(x) -> ([], [x])
  | Tree {operator=op; left=l; right=r} ->
    let (left_op, left_values) = nodes_and_leaves l in
    let (right_op, right_values) = nodes_and_leaves r in
    (op :: left_op @ right_op, left_values @ right_values)

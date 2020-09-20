(* Question 2.2 *)
type expr =
  | Const of int
  | Var of string
  | Plus of args
  | Mult of args
  | Minus of args
  | Div of args
and args = {arg1:expr; arg2:expr}

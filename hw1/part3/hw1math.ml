(* Question 2.2 *)
type expr =
  | Const of int
  | Var of string
  | Plus of args
  | Mult of args
  | Minus of args
  | Div of args
and args = {arg1:expr; arg2:expr}


(* Question 2.3 *)
let rec evaluate arith_expr =
  match arith_expr with
  | _ -> failwith "error"
  | Const value ->
  | Var value ->
  | Plus {arg1=a1; arg2=a2} ->
  | Mult {arg1=a1; arg2=a2} ->
  | Minus {arg1=a1; arg2=a2} ->
  | Div {arg1=a1; arg2=a2} ->

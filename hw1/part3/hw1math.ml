(* Question 2.2 *)
type 'a expr =
  | Const of 'a
  | Var of string
  | Plus of 'a args
  | Mult of 'a args
  | Minus of 'a args
  | Div of 'a args
and 'a args = {arg1: 'a expr; arg2: 'a expr}


(* Question 2.3 *)
let rec evaluate arith_expr =
  match arith_expr with
  | Const value -> value
  | Plus {arg1=a1; arg2=a2} -> (evaluate a1) + (evaluate a2)
  | Mult {arg1=a1; arg2=a2} -> (evaluate a1) * (evaluate a2)
  | Minus {arg1=a1; arg2=a2} -> (evaluate a1) - (evaluate a2)
  | Div {arg1=a1; arg2=a2} -> (evaluate a1) / (evaluate a2)
  | _ -> failwith "expression couldn't be evaluated"

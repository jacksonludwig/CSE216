(* Question 2.1 *)
type bool_expr =
  | Lit of string
  | Not of bool_expr
  | And of bool_expr * bool_expr
  | Or of bool_expr * bool_expr

let rec calc_bool lit_1 bool_1 lit_2 bool_2 expr =
  match expr with
  | Lit value ->
    if value = lit_1 then bool_1 else bool_2
  | Not left_over ->
    not(calc_bool lit_1 bool_1 lit_2 bool_2 left_over)
  | And(left_1,left_2) ->
    calc_bool lit_1 bool_1 lit_2 bool_2 left_1 && calc_bool lit_1 bool_1 lit_2 bool_2 left_2
  | Or(left_1, left_2) ->
    calc_bool lit_1 bool_1 lit_2 bool_2 left_1 || calc_bool lit_1 bool_1 lit_2 bool_2 left_2

let truth_table lit_1 lit_2 expr =
  [(true, true, calc_bool lit_1 true lit_2 true expr);
   (true, false, calc_bool lit_1 true lit_2 false expr);
   (false, true, calc_bool lit_1 false lit_2 true expr);
   (false, false, calc_bool lit_1 false lit_2 false expr)]

let x = truth_table "a" "b" (And(Lit("a"), Lit("b")))

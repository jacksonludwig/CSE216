(* Question 1 *)
let rec pow (x:int) (n:int) =
  if n = 0 then 1 else x * pow x (n - 1)

let rec float_pow (x:float) (n:int) =
  if n = 0 then 1.0 else x *. float_pow x (n - 1)


(* Question 2 *)
let rec compress list =
  match list with
  | [] -> []
  | hd::[] -> hd::[]
  | hd::next::tl ->
    if hd = next then compress (next::tl)
    else hd::compress (next::tl)


(* Question 3 *)
let rec remove_if list pred =
  match list with
  | [] -> []
  | hd::tl ->
    if pred hd then remove_if tl pred
    else hd::remove_if tl pred


(* Question 4 *)

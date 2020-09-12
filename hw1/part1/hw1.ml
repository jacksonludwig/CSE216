(* Question 1 *)
let rec pow (x:int) (n:int) =
  if n = 0 then 1 else x * pow x (n - 1)

let rec float_pow (x:float) (n:int) =
  if n = 0 then 1.0 else x *. float_pow x (n - 1)


(* Question 2 *)
let prepend_if_same acc x =
  match acc with
  | [] -> [x]
  | hd::_ -> if x = hd then acc else x::acc

let compress list =
  List.fold_right(fun x acc -> prepend_if_same acc x) list []

(* Question 3 *)
let remove_if list pred =
  List.fold_right(fun x acc -> if pred x then acc else x::acc) list []


(* Question 4 *)

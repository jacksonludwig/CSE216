(* Question 1 *)
let rec pow (x:int) (n:int) =
  if n = 0 then 1 else x * pow x (n - 1)

let rec float_pow (x:float) (n:int) =
  if n = 0 then 1.0 else x *. float_pow x (n - 1)


(* Question 2 *)
let list_reverse list =
  List.fold_left(fun acc x -> x :: acc) [] list

let prepend_if_same acc x =
  match acc with
  | [] -> [x]
  | hd::_ -> if x = hd then acc else x::acc

let compress list =
  list_reverse (List.fold_left(fun acc x -> prepend_if_same acc x) [] list)

let x = [1; 2; 2; 3; 4; 5; 5; 5; 6; 2; 5; 1; 7]
let x = compress x

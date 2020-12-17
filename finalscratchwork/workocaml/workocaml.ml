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
let rec slice list first last =
  if last = 0 then []
  else
    match list with
    | [] -> []
    | hd::tl ->
      if first <> 0 then slice tl (first - 1) (last - 1)
      else hd::slice tl first (last - 1)


(* Question 5 *)
let rec compare fn item list =
  match list with
  | [] -> []
  | hd::tl ->
    if fn item hd then hd::compare fn item tl
    else compare fn item tl

let rec helper fn list count =
  if count < 1 then [] else
    match list with
    | [] -> []
    | hd::tl ->
      let compared = compare fn hd list in
      compared::helper fn tl (count - List.length compared)

let equivs fn list =
  let size = List.length list in
  helper fn list size


(* Question 6 *)
let rec primehelper num divisor =
  if divisor = 1 then true
  else if num mod divisor = 0 then false
  else primehelper num (divisor - 1)

let is_prime num =
  primehelper num (num - 1)

let rec goldbachhelper start num_2 =
  if is_prime num_2 && is_prime (start - num_2) then (num_2, start - num_2)
  else goldbachhelper start (num_2 + 1)

let goldbachpair num =
  goldbachhelper num 2


(* Question 7 *)
let rec equiv_on f g lst =
  match lst with
  | [] -> true
  | hd::tl ->
    if f hd = g hd then equiv_on f g tl
    else false


(* Question 8 *)
let rec pairwisefilter cmp lst =
  match lst with
  | [] -> []
  | hd::[] -> hd::[]
  | hd::next::tl ->
    (cmp hd next)::pairwisefilter cmp tl


(* Question 9 *)
let apply_to_both f1 f2 =
  fun x -> f1 x + f2 x

let rec polynomial tuples =
  match tuples with
  | [] -> fun _ -> 0
  | [(hd1,hd2)] ->
    fun x -> hd1 * (pow x hd2)
  | (hd1,hd2)::tl ->
    apply_to_both (fun x -> hd1 * (pow x hd2)) (polynomial tl)


(* Question 10 *)
let rec my_map func list =
  match list with
  | [] -> []
  | hd::tl ->
    (func hd)::(my_map func tl)

let rec powerset list =
  match list with
  | [] -> [[]]
  | hd::tl ->
    powerset tl @ my_map (fun itl -> hd :: itl) (powerset tl)

let rec add_if bval ele list =
  if bval then ele::list
  else list

let x = add_if true 1 [1;2;3]

(* EXTRA *)
(* let rec list_print list = *)
(* match list with *)
(* | [] -> () *)
(* | hd::tl -> *)
(* print_string hd; *)
(* list_print(tl) *)


(* type 'a binary_tree =
 *   | Empty
 *   | Node of 'a * 'a binary_tree * 'a binary_tree
 *
 * let rec is_mirror tree1 tree2 =
 *   match tree1, tree2 with
 *   | Empty, Empty -> true
 *   | Empty, _ -> false
 *   | _, Empty -> false
 *   | Node(val1, left1, right1), Node(val2, left2, right2) ->
 *     (val1 = val2 ) && (is_mirror(left1, right2)) && (is_mirror(right1, left2)) *)

(* let reverse my_list =
 *   let rec rev_helper acc list =
 *     match list with
 *     | [] -> acc
 *     | h::t -> rev_helper (h::acc) t in
 *   let reversed = fun list -> rev_helper [] list in
 *   match my_list, reversed with
 *   | [], [] -> true
 *   | hd1::tl1, hd2::tl2 ->
 *     if hd1 = hd2 then rev_helper(tl1, tl2) else false *)


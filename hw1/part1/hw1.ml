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

let x = [1;2;3;4;5;6;7;8]
let x = equivs (fun x y -> x mod 2 = y mod 2) x


(* Question 6 *)

let rec trim list =
  let rec helper l dupe_list =
    match l with
    | [] -> []
    | hd::tl ->
      if List.mem hd dupe_list then helper tl dupe_list
      else hd::(helper tl (hd::dupe_list)) in
  helper list []

let x = trim [1; 2; 3; 4; 4; 4; 1; 1; 2; 8; 8]

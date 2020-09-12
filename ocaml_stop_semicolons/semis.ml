let foo x y =
  let _ = print_endline "About to add two numbers" in
  let _ = print_endline "Almost there" in
  x + y

let foo_sugar x y =
  print_endline "About to add two numbers";
  print_endline "Almost there";
  x + y

let _ = print_endline (string_of_int (foo 2 2))

let thing x y z =
  let x = x + 5 in
  let y = y + 5 in
  x + y + z

let _ = print_string (String.concat ", " (List.map (fun x -> string_of_int (x * 2)) [1; 2; 3]))

let rec pow x n =
  if n = 0 then 1 else x * pow x (n - 1)

let _ = print_endline (string_of_int (pow 5 2))

let foo x y =
  let _ = print_endline "About to add two numbers" in
  let _ = print_endline "Almost there" in
  x + y

let _ = print_endline (string_of_int (foo 2 2))

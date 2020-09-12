print_string "Hello world\n";;

let average a b =
  (a +. b) /. 2.0;;

let x = average 10.0 5.0 in print_float x;;

let f a b =
  let x = a +. b in
  x +. x ** 2.;;

let my_ref = ref 0;;
my_ref := 100;;
print_int !my_ref;;

(* STOP USING ;; *)
let _ = print_endline "hello world"

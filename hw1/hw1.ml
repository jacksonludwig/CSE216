let rec pow (x:int) (n:int) =
  if n = 0 then 1 else x * pow x (n - 1)

let rec float_pow (x:float) (n:int) =
  if n = 0 then 1.0 else x *. float_pow x (n - 1)

let _ = print_endline (string_of_float (float_pow 2.5 4))

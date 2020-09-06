fn recursive_mult(x: u32, y: u32) -> u32 {
  if y != 0 {
    return x + recursive_mult(x, y - 1);
  }
  return 0;
}

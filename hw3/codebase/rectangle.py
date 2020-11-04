from .quadrilateral import Quadrilateral
from .two_d_point import TwoDPoint


class Rectangle(Quadrilateral):

    def __init__(self, *floats):
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError(
                "A rectangle cannot be formed by the given coordinates.")

    def __is_member(self):
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        verts = self.vertices
        for v in range(0, 4):
            for v2 in range(0, 4):
                if v != v2:
                    if verts[v] == verts[v2]:
                        return False

        lengths = self.side_lengths()
        if lengths[0] != lengths[2]:
            return False
        if lengths[1] != lengths[3]:
            return False
        return True  # was TODO

    def center(self):
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""
        verts = self.vertices
        center_x = (verts[0].x + verts[2].x) / 2
        center_y = (verts[0].y + verts[2].y) / 2
        return TwoDPoint(center_x, center_y)  # was TODO

    def area(self):
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""
        side_lengths = self.side_lengths()
        return side_lengths[0] * side_lengths[1]  # was TODO

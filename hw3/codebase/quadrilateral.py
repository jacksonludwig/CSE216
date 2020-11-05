from .two_d_point import TwoDPoint


class Quadrilateral:

    def __init__(self, *floats):
        # this if was added because only 8 floats make 4 points
        if len(floats) != 8:
            raise ValueError(
                "Only 8 floats can make a shape")

        points = TwoDPoint.from_coordinates(list(floats))
        self.__vertices = tuple(points[0:4])  # changed from 0:3

    @property
    def vertices(self):
        return self.__vertices

    def side_lengths(self):
        """Returns a tuple of four floats, each denoting the length of a side of this quadrilateral. The value must be
        ordered clockwise, starting from the top left corner."""
        side1 = abs(self.vertices[1].x - self.vertices[0].x)
        side2 = abs(self.vertices[1].y - self.vertices[2].y)
        side3 = abs(self.vertices[2].x - self.vertices[3].x)
        side4 = abs(self.vertices[0].y - self.vertices[3].y)
        return side1, side2, side3, side4  # was TODO

    def smallest_x(self):
        """Returns the x-coordinate of the vertex with the smallest x-value of the four vertices of this
        quadrilateral."""
        return min(map(lambda v: v.x, self.vertices))  # was TODO

    def __eq__(self, shape): # implemented by me
        if type(self) != type(shape):
            return False
        for i in range(0, 4):
            if self.vertices[i] != shape.vertices[i]:
                return False
        return True

    def __str__(self): # implemented by me
        verts_str = str(self.vertices[0])
        for v in self.vertices[1:]:
            verts_str += '; '
            verts_str += str(v)
        return self.__class__.__name__ + ": " + verts_str

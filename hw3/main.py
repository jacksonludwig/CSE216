from package.project.quadrilateral import Quadrilateral
from package.project.rectangle import Rectangle
from package.project.sorter import ShapeSorter
from package.project.square import Square
from package.project.two_d_point import TwoDPoint

# import unittest


def main():
    # unittest.main()

    points = TwoDPoint.from_coordinates([2, 2, 3.0, 3.1, 2.3, 1.2])
    for p in points:
        print(p)

    p1 = TwoDPoint(2, 2)
    p2 = TwoDPoint(4, 4)

    print(p1 - p2)

    p3 = TwoDPoint(2.2, 2.6)
    print(p1 == 2)

    li = [1, 2,3,4,5,6]
    print(li[0:3])

    q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
    print(q.smallest_x())
    print(q.side_lengths())
    r = Rectangle(-1, 0, 0, 0, 0, -1, -1, -1)
    r2 = Rectangle(0, 1, 1, 1, 1, 0, 0, 0)

    print("area: " + str(r.area()))
    print("center: " + str(r2.center()))

    print(Square.round_point(p3))

    s = Square(0, 1, 1, 1, 1, 0, 0, 0)
    s2 = Square(0, .6, .6, .6, .6, 0, 0, 0)
    print(s == s2.snap())

    q3 = Rectangle(6, 3, 2, 3, 2, 1, 6, 1)
    shapes = [s, q3, r, q]
    for s in shapes:
        print(s)
    print()
    for s in ShapeSorter.sort(*tuple(shapes)):
        print(s)

    print(Square.round_point(TwoDPoint(-1.5, 1.4)))

main()

from package.project.two_d_point import TwoDPoint
from unittest import TestCase
from ..project.quadrilateral import Quadrilateral


class TestQuadrilateral(TestCase):
    def test_side_lengths(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)  # was TODO
        self.assertEqual((1, 1, 1, 1), q.side_lengths())
        self.assertNotEqual((-1, -1, -1, -1), q.side_lengths())

    def test_smallest_x(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)  # was TODO
        self.assertEqual(0, q.smallest_x())
        q = Quadrilateral(0, 1, 1, 1, 1, -1, 0, 0)
        self.assertNotEqual(-1, q.smallest_x())
        q = Quadrilateral(0, 1, 1, 1, -2, -1, 0, 0)
        self.assertEqual(-2, q.smallest_x())

    def test___init__(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
        self.assertEqual(
            q.vertices,
            (TwoDPoint(0, 1), TwoDPoint(1, 1), TwoDPoint(1, 0), TwoDPoint(0, 0)),
        )
        with self.assertRaises(ValueError):
            q = Quadrilateral(2.2, 3.1)

    def test___eq__(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
        p = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 1)
        self.assertFalse(p == q)
        q2 = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
        p2 = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
        self.assertTrue(q2 == p2)
        self.assertTrue(p2 == p2)

    def test___str__(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0)
        self.assertEqual("Quadrilateral: (0, 1); (1, 1); (1, 0); (0, 0)", str(q))

from unittest import TestCase
from ..project.quadrilateral import Quadrilateral


class TestQuadrilateral(TestCase):

    def test_side_lengths(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0) # was TODO
        self.assertEqual((1, 1, 1, 1), q.side_lengths())
        self.assertNotEqual((-1, -1, -1, -1), q.side_lengths())

    def test_smallest_x(self):
        q = Quadrilateral(0, 1, 1, 1, 1, 0, 0, 0) # was TODO
        self.assertEqual(0, q.smallest_x())
        q = Quadrilateral(0, 1, 1, 1, 1, -1, 0, 0)
        self.assertNotEqual(-1, q.smallest_x())
        q = Quadrilateral(0, 1, 1, 1, -2, -1, 0, 0)
        self.assertEqual(-2, q.smallest_x())

    def test___init__(self):
        with self.assertRaises(ValueError):
            q = Quadrilateral(2.2, 3.1)

from unittest import TestCase
from ..project.two_d_point import TwoDPoint


class TestTwoDPoint(TestCase):

    def test_from_coordinates(self):
        self.assertEqual([TwoDPoint(0, 0), TwoDPoint(
            1.2, 3.2), TwoDPoint(-5, 3)], TwoDPoint.from_coordinates([0, 0, 1.2, 3.2, -5, 3]))
        self.assertNotEqual([TwoDPoint(0, 0), TwoDPoint(
            1.2, 3.2), TwoDPoint(-5, 3)], TwoDPoint.from_coordinates([0, 0, 1.2, 3.2, -4, 3]))

    def test___eq__(self):
        point_1 = TwoDPoint(2, 2)
        point_2 = TwoDPoint(2, 2)
        self.assertEqual(point_1, point_2)
        point_3 = TwoDPoint(2, 2.1)
        self.assertNotEqual(point_1, point_3)

    def test___add__(self):
        point_1 = TwoDPoint(2, 2)
        point_2 = TwoDPoint(2, 2)
        point_3 = TwoDPoint(4, 4)
        self.assertEqual(point_1 + point_2, point_3)
        self.assertNotEqual(point_1 + point_3, point_2)

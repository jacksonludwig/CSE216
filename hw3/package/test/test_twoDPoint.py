from unittest import TestCase
from ..project.two_d_point import TwoDPoint


class TestTwoDPoint(TestCase):

    def test_from_coordinates(self):
        self.assertEqual([TwoDPoint(0, 0), TwoDPoint(
            1.2, 3.2), TwoDPoint(-5, 3)], TwoDPoint.from_coordinates([0, 0, 1.2, 3.2, -5, 3]))
        self.assertNotEqual([TwoDPoint(0, 0), TwoDPoint(
            1.2, 3.2), TwoDPoint(-5, 3)], TwoDPoint.from_coordinates([0, 0, 1.2, 3.2, -4, 3]))



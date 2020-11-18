from package.project.two_d_point import TwoDPoint
from package.project.rectangle import Rectangle
from unittest import TestCase

class TestRectange(TestCase):

    def test_center(self):
        r = Rectangle(0, 1, 1, 1, 1, 0, 0, 0)
        self.assertEqual(TwoDPoint(0.5, 0.5), r.center())
        r2 = Rectangle(-1, 2, 2, 2, 2, -1, -1, -1)
        self.assertEqual(r.center(), r2.center())

    def test_area(self):
        r = Rectangle(0, 1, 1, 1, 1, 0, 0, 0)
        self.assertEqual(1, r.area())
        r2 = Rectangle(0, 2, 5, 2, 5, 0, 0, 0)
        self.assertEqual(10, r2.area())
        r3 = Rectangle(0, 2, -5, 2, -5, 0, 0, 0)
        self.assertEqual(10, r3.area())

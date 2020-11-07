from package.project.square import Square
from unittest import TestCase

class TestSquare(TestCase):

    def test_snap(self):
        s = Square(0, 1, 1, 1, 1, 0, 0, 0)
        s2 = Square(0, .6, .6, .6, .6, 0, 0, 0)
        s3 = Square(0, .4, .4, .4, .4, 0, 0, 0)
        self.assertNotEqual(s, s2)
        self.assertEqual(s, s2.snap())
        self.assertNotEqual(s, s3.snap())
        self.assertNotEqual(s3, s3.snap()) # although the points are the same, the shape is not, so it should not be equal
        self.assertNotEqual(s, s3)
        self.assertEqual(s, s.snap())

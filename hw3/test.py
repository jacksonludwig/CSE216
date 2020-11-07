from package.test import test_twoDPoint
from package.test import test_quadrilateral
from package.test import test_rectangle
from package.test import test_square
import unittest

unittest.main(test_twoDPoint, exit=False)
unittest.main(test_quadrilateral, exit=False)
unittest.main(test_rectangle, exit=False)
unittest.main(test_square)

from typing import Tuple
import json
import pygame

from math import hypot


class Challenge:
    def __init__(self, color: str, position: Tuple[int, int], solved):
        self.color = color
        self.position = position
        self.solved = lambda: solved() or json.load(open("solves.json", "r"))[color]

    def draw_challenge(self, surface: pygame.Surface, main_screen=False):
        pygame.draw.circle(
            surface,
            pygame.Color(self.color),
            (
                surface.get_width() * (self.position[0] if main_screen else 0.5),
                surface.get_height() * (self.position[1] if main_screen else 0.5),
            ),
            20,
            width=20 if self.solved() else 5,
        )

    def clicked(self, surface: pygame.Surface, click_position: Tuple[int, int]) -> bool:
        return (
            hypot(
                click_position[0] - surface.get_width() * self.position[0],
                click_position[1] - surface.get_height() * self.position[1],
            )
            < 20
        )

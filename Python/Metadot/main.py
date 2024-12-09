from typing import Dict, List
import pygame
import json
from datetime import datetime
from time import sleep
from sys import argv

from challenge import Challenge

WIDTH = 600
HEIGHT = 300


def main_screen(surface: pygame.Surface, challenges: List):
    surface.fill(pygame.Color("gray16"))
    for challenge in challenges:
        challenge.draw_challenge(surface, True)


def draw_blue(surface: pygame.Surface, challenge: Challenge):
    surface.fill(pygame.Color("gray16"))
    challenge.draw_challenge(surface)
    font = pygame.freetype.SysFont("Arial", 30)
    font.render_to(
        surface,
        (surface.get_width() / 2.4, surface.get_height() / 6),
        "main.py",
        pygame.Color("blue"),
    )

    if challenge.solved():
        with open("solves.json", "r") as solves_file:
            solves: Dict = json.load(solves_file)

        solves["blue"] = True

        with open("solves.json", "w") as solves_file:
            json.dump(solves, solves_file, indent=4)


def draw_yellow(surface: pygame.Surface, challenge: Challenge):
    surface.fill(pygame.Color("gray16"))
    challenge.draw_challenge(surface)
    font = pygame.freetype.SysFont("Arial", 30)
    font.render_to(
        surface,
        (surface.get_width() / 3, surface.get_height() / 6),
        '{"yellow": false}',
        pygame.Color("yellow"),
    )

    if challenge.solved():
        with open("solves.json", "r") as solves_file:
            solves: Dict = json.load(solves_file)

        solves["yellow"] = True

        with open("solves.json", "w") as solves_file:
            json.dump(solves, solves_file, indent=4)


def draw_green(surface: pygame.Surface, challenge: Challenge):
    surface.fill(pygame.Color("gray16"))
    challenge.draw_challenge(surface)
    font = pygame.freetype.SysFont("Arial", 30)
    font.render_to(
        surface,
        (surface.get_width() / 4, surface.get_height() / 6),
        "This text is too long, I can't see all of it!         Or can I?",
        pygame.Color("green"),
    )

    if challenge.solved():
        with open("solves.json", "r") as solves_file:
            solves: Dict = json.load(solves_file)

        solves["green"] = True

        with open("solves.json", "w") as solves_file:
            json.dump(solves, solves_file, indent=4)


def draw_red(surface: pygame.Surface, challenge: Challenge):
    surface.fill(pygame.Color("gray16"))
    challenge.draw_challenge(surface)
    font = pygame.freetype.SysFont("Arial", 30)
    font.render_to(
        surface,
        (surface.get_width() / 3, surface.get_height() / 6),
        'argv[1] == "red"',
        pygame.Color("red"),
    )

    if challenge.solved():
        with open("solves.json", "r") as solves_file:
            solves: Dict = json.load(solves_file)

        solves["red"] = True

        with open("solves.json", "w") as solves_file:
            json.dump(solves, solves_file, indent=4)


def main():
    run = True
    pygame.init()
    pygame.display.set_caption("Metadot")
    surface = pygame.display.set_mode((WIDTH, HEIGHT), pygame.RESIZABLE)

    blue = Challenge("blue", (0.25, 0.25), lambda: False)
    yellow = Challenge(
        "yellow",
        (0.75, 0.25),
        lambda: json.load(open("solves.json", "r"))["yellow"],
    )
    green = Challenge(
        "green",
        (0.25, 0.75),
        lambda: surface.get_width() > 900,
    )
    red = Challenge("red", (0.75, 0.75), lambda: len(argv) > 1 and argv[1] == "red")

    challenges = [blue, yellow, green, red]

    draw_unsolved: Dict = {
        blue: draw_blue,
        yellow: draw_yellow,
        green: draw_green,
        red: draw_red,
    }

    main_screen(surface, challenges)

    while run:
        pygame.display.flip()

        for event in pygame.event.get():
            match event.type:
                case pygame.QUIT:
                    run = False
                case pygame.KEYDOWN:
                    match event.key:
                        case pygame.K_m:
                            main_screen(surface, challenges)
                case pygame.MOUSEBUTTONDOWN:
                    for challenge in challenges:
                        if challenge.clicked(surface, pygame.mouse.get_pos()):
                            draw_unsolved[challenge](surface, challenge)
    pygame.quit()


if __name__ == "__main__":
    main()

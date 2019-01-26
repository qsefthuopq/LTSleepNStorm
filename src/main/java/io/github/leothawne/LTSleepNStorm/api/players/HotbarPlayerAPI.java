package io.github.leothawne.LTSleepNStorm.api.players;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HotbarPlayerAPI {
    private static Class<?> CRAFTPLAYERCLASS;
    private static Class<?> PACKET_PLAYER_CHAT_CLASS;
    private static Class<?> ICHATCOMP;
    private static Class<?> CHATMESSAGE;
    private static Class<?> PACKET_CLASS;
    private static Class<?> CHAT_MESSAGE_TYPE;
    private static Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR;
    private static Constructor<?> CHATMESSAGE_CONSTRUCTOR;
    private static final String SERVER_VERSION;
    private static Method GET_CHAT_MESSAGE_TYPE;
    static {
        String name = Bukkit.getServer().getClass().getName();
        name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
        name = name.substring(0, name.indexOf("."));
        SERVER_VERSION = name;
        try {
            CRAFTPLAYERCLASS = Class.forName("org.bukkit.craftbukkit." + SERVER_VERSION + ".entity.CraftPlayer");
            PACKET_PLAYER_CHAT_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".PacketPlayOutChat");
            PACKET_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".Packet");
            ICHATCOMP = Class.forName("net.minecraft.server." + SERVER_VERSION + ".IChatBaseComponent");
            CHAT_MESSAGE_TYPE = Class.forName("net.minecraft.server." + SERVER_VERSION + ".ChatMessageType");
            PACKET_PLAYER_CHAT_CONSTRUCTOR = Optional.of(PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP, CHAT_MESSAGE_TYPE)).get();
            CHATMESSAGE = Class.forName("net.minecraft.server." + SERVER_VERSION + ".ChatMessage");
            GET_CHAT_MESSAGE_TYPE = CHAT_MESSAGE_TYPE.getDeclaredMethod("a", Byte.TYPE);
            try {
                CHATMESSAGE_CONSTRUCTOR = Optional.of(CHATMESSAGE.getConstructor(String.class, Object[].class)).get();
            } catch (NoSuchMethodException e) {
                CHATMESSAGE_CONSTRUCTOR = Optional.of(CHATMESSAGE.getDeclaredConstructor(String.class, Object[].class)).get();
            }
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
    public static void sendMessage(Player player, String message) {
        try {
            Object icb = CHATMESSAGE_CONSTRUCTOR.newInstance(message, new Object[0]);
            Object cmt = GET_CHAT_MESSAGE_TYPE.invoke(CHAT_MESSAGE_TYPE, (byte) 2);
            Object packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, cmt);
            Object craftplayerInst = CRAFTPLAYERCLASS.cast(player);
            Optional<Method> methodOptional = Optional.of(CRAFTPLAYERCLASS.getMethod("getHandle"));
            Object methodhHandle = methodOptional.get().invoke(craftplayerInst);
            Object playerConnection = methodhHandle.getClass().getField("playerConnection").get(methodhHandle);
            Optional.of(playerConnection.getClass().getMethod("sendPacket", PACKET_CLASS)).get().invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}